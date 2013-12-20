/*
 * This source code is copyright (c) 2012 Pixelus Consulting Pty Ltd.  All
 * rights reserved.
 *
 * Should you wish to use or enquire about any of the content contained please
 * contact David Mouser (david.mouser@gmail.com).
 */

(function (global) {

  var spyMatchers = "called calledOnce calledTwice calledThrice calledBefore calledAfter calledOn alwaysCalledOn calledWith alwaysCalledWith calledWithExactly alwaysCalledWithExactly".split(" "),
      i = spyMatchers.length,
      spyMatcherHash = {},
      unusualMatchers = {
        "returned":"toHaveReturned",
        "alwaysReturned":"toHaveAlwaysReturned",
        "threw":"toHaveThrown",
        "alwaysThrew":"toHaveAlwaysThrown"
      },

      getMatcherFunction = function (sinonName) {
        return function () {
          var sinonProperty = this.actual[sinonName];
          return (typeof sinonProperty === 'function') ? sinonProperty.apply(this.actual, arguments) : sinonProperty;
        };
      };

  while (i--) {
    var sinonName = spyMatchers[i],
        matcherName = "toHaveBeen" + sinonName.charAt(0).toUpperCase() + sinonName.slice(1);

    spyMatcherHash[matcherName] = getMatcherFunction(sinonName);
  }
  ;

  for (var j in unusualMatchers) {
    spyMatcherHash[unusualMatchers[j]] = getMatcherFunction(j);
  }

  global.sinonJasmine = {
    getMatchers:function () {
      return spyMatcherHash;
    }
  };

})(window);

beforeEach(function () {

  this.addMatchers(sinonJasmine.getMatchers());

});
