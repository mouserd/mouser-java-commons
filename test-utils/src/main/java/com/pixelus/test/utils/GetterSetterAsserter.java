/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

package com.pixelus.test.utils;

/*
 * @author David Mouser
 */

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * This is an abstract class for testing 'entity' objects.  Classes that
 * test entity objects can extend this to provide convenient methods for
 * testing getters/setters.
 *
 * @author mouserd
 */
public class GetterSetterAsserter {

    /**
     * Default test size used when testing primitive arrays.
     */
    private static final int TEST_ARRAY_SIZE = 10;

    /**
     * The default supported class types - users of this class can
     * add/remove these using the relevant methods.
     */
    @SuppressWarnings("unchecked")
    private final HashMap<Class, Object> supportedClassTypes;

    /**
     * Default Constructor.
     */
    @SuppressWarnings("unchecked")
    public GetterSetterAsserter() {

        supportedClassTypes = new HashMap<Class, Object>();
        supportedClassTypes.put(List.class, new ArrayList());
        supportedClassTypes.put(Set.class, new HashSet());
        supportedClassTypes.put(SortedSet.class, new TreeSet());
        supportedClassTypes.put(Map.class, new HashMap());
        supportedClassTypes.put(SortedMap.class, new TreeMap());
        supportedClassTypes.put(Boolean.class, true);
        supportedClassTypes.put(Boolean.TYPE, true);
        supportedClassTypes.put(Character.class, 'a');
        supportedClassTypes.put(Character.TYPE, 'a');
        supportedClassTypes.put(Byte.class, (byte) 1);
        supportedClassTypes.put(Byte.TYPE, (byte) 1);
        supportedClassTypes.put(Short.class, (short) 1);
        supportedClassTypes.put(Short.TYPE, (short) 1);
        supportedClassTypes.put(Integer.class, 1);
        supportedClassTypes.put(Integer.TYPE, 1);
        supportedClassTypes.put(Long.class, 1L);
        supportedClassTypes.put(Long.TYPE, 1L);
        supportedClassTypes.put(Float.class, 1F);
        supportedClassTypes.put(Float.TYPE, 1F);
        supportedClassTypes.put(Double.class, 1.0);
        supportedClassTypes.put(Double.TYPE, 1.0);
        supportedClassTypes.put(Date.class, new Date());
        supportedClassTypes.put(Timestamp.class, new Timestamp(new Date().getTime()));
        supportedClassTypes.put(BigDecimal.class, BigDecimal.ONE);
    }

    /**
     * Add the given type for the Map of supported-types.
     *
     * @param type  The Class type to add.
     * @param value The value of the given type to use during the test.
     */
    @SuppressWarnings("unchecked")
    public void addSupportedType(final Class type, final Object value) {

        supportedClassTypes.put(type, value);
    }

    /**
     * Remove the given type for the Map of supported-types.
     *
     * @param type The Class type to remove.
     */
    @SuppressWarnings("unchecked")
    public void removeSupportedType(final Class type) {

        supportedClassTypes.remove(type);
    }

    /**
     * Test all getters and setters on the given target object.
     *
     * @param target The object test getters and setters for.
     */
    public void assertAllGettersAndSetters(final Object target) {

        assertAllGettersAndSettersWithExclusions(target);
    }

    /**
     * Test the target objects getters and setters but omitting the passed property
     * name var-args.
     *
     * @param target        The object test getters and setters for.
     * @param propertyNames The property names to exclude.
     */
    public void assertAllGettersAndSettersWithExclusions(final Object target,
                                                         final String... propertyNames) {

        final List<String> blacklist = Arrays.asList(propertyNames);

        try {

            final BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass());
            final PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor descriptor : descriptors) {

                // If there is no setter then skip this property.
                if (descriptor.getWriteMethod() == null) {

                    continue;
                }

                if (!blacklist.contains(descriptor.getDisplayName())) {

                    assertGettersAndSetters(target, descriptor.getDisplayName());
                }
            }
        } catch (final IntrospectionException e) {

            fail("Failed while introspecting target " + target.getClass());
        }
    }

    /**
     * Test the target objects given getter and setter properties.
     *
     * @param target        The object test getters and setters for.
     * @param propertyNames The property names to test.
     */
    public void assertGettersAndSetters(final Object target,
                                        final String... propertyNames) {

        final Map<String, Object> properties = new LinkedHashMap<String, Object>();

        for (String propertyName : propertyNames) {

            properties.put(propertyName, null);
        }

        assertGettersAndSetters(target, properties);
    }

    /**
     * Test the target objects getters and setters for the properties that match
     * those given in the passed Map.  The map should set any value it wishes to use
     * during the getter/setter method if it doesn't want one generated from the list
     * of known types.
     *
     * @param target     The object test getters and setters for.
     * @param properties A map of the properties and corresponding values to test.
     */
    public void assertGettersAndSetters(final Object target,
                                        final Map<String, Object> properties) {

        final Set<Map.Entry<String, Object>> entries = properties.entrySet();

        for (Map.Entry<String, Object> entry : entries) {

            assertGetterAndSetter(target, entry.getKey(), entry.getValue());
        }
    }

    /**
     * Test the target objects getter and setter for a given property.  The
     * value to use during the test will be determined from a list of 'known' types.
     *
     * @param target   The object test getters and setters for.
     * @param property The single property getter/setter to test.
     * @see GetterSetterAsserter#addSupportedType(Class, Object)
     * @see GetterSetterAsserter#removeSupportedType(Class)
     */
    public void assertGetterAndSetter(final Object target,
                                      final String property) {

        assertGetterAndSetter(target, property, null);
    }

    /**
     * Validate the target objects getter and setter for the given property using
     * the passed argument as the test value.
     *
     * @param target   The object test getters and setters for.
     * @param property The single property getter/setter to test.
     * @param argument The argument to test against.
     * @see GetterSetterAsserter#addSupportedType(Class, Object)
     * @see GetterSetterAsserter#removeSupportedType(Class)
     */
    @SuppressWarnings("unchecked")
    public void assertGetterAndSetter(final Object target,
                                      final String property, final Object argument) {

        try {

            final PropertyDescriptor descriptor =
                  new PropertyDescriptor(property, target.getClass());
            Object setterArg = argument;

            final Class type = descriptor.getPropertyType();

            // If the argument to test with was not provided then
            // attempt to generate one using basic types or obtaining
            // one from the list of 'known types' Map.
            // If one still cannot be found then the property cannot
            // be tested.
            if (setterArg == null) {

                if (type.isArray()) {

                    setterArg = Array.newInstance(type.getComponentType(),
                          new int[]{TEST_ARRAY_SIZE});

                } else if (type.isEnum()) {

                    setterArg = type.getEnumConstants()[0];

                } else if (supportedClassTypes.containsKey(type)) {

                    setterArg = supportedClassTypes.get(type);

                } else if (!type.isInterface()) {

                    // Attempt to construct a new instance of this type
                    // using it's default constructor.
                    final Constructor constructor = type.getDeclaredConstructor();

                    // Handle private constructors.
                    constructor.setAccessible(true);
                    setterArg = constructor.newInstance();
                }
            }

            // If the class of the setter argument is deemed to be an 'Object' then
            // we can't test it.  This is to fix an issue where a generic type cannot
            // be determined at runtime and was causing tests to fail during
            // cobertura instrumentation due to the generics information no longer being
            // available.
            if ((setterArg != null) && (setterArg.getClass() == Object.class)) {

                System.err.println("Property '" + property + "' not tested as "
                      + "return/parameter type is an Object [possibly due to generics]");
                return;
            }

            assertNotNull("Unsupported type '" + type.getName() + "'. "
                  + "Unable to test getter/setter for property " + property, setterArg);

            // Attempt to invoke the setter with the sample test argument
            // and then verify that the getter returns the same value.
            final Method setterMethod = descriptor.getWriteMethod();
            final Method getterMethod = descriptor.getReadMethod();

            setterMethod.invoke(target, setterArg);
            final Object getterValue = getterMethod.invoke(target);

            // Ensure that the value given to the setter is equal to that returned
            // from the getter.
            assertEquals("Failed to validate getter/setter for property '"
                  + property + "'", setterArg, getterValue);

        } catch (final IntrospectionException e) {

            fail("Error creating PropertyDescriptor for property [" + property
                  + "]. Do you have a getter and a setter? (property: " + property + ")");
        } catch (final IllegalAccessException e) {

            fail("Error accessing property. Are the getter and setter both accessible?"
                  + "(property: " + property + ")");
        } catch (final InvocationTargetException e) {

            fail("Error invoking method on target " + property);
        } catch (final InstantiationException e) {

            fail("Error instantiating instance");
        } catch (final NoSuchMethodException e) {

            fail("No such method: " + e.getMessage());
        }
    }
}
