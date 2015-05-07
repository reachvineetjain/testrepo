require.config({
  paths: {
    angular: '/angular/angular',
    text: '/requirejs-text/text',
    ui: '/core/app',
    uiRouter: '/angular-ui-router/release/angular-ui-router',
    module: '/module',
    uiSelect: '/angular-ui-select/dist/select',
    coreServices: '/core/services'
  },
  shim: {
    'angular' : {'exports' : 'angular'},
    'ui': ['angular'],
    'uiRouter': ['angular'],
    'module': ['angular']
  },
  priority: [
    "angular"
  ]
});

require([
    'angular',
    'ui',
    '/module/' + GO.module + '/main.js'
  ], function(angular) {
    var $html = angular.element(document.getElementsByTagName('html')[0]);
    angular.element().ready(function() {
      // bootstrap the app manually
      angular.bootstrap(document, ['ui']);
    });
  }
);
