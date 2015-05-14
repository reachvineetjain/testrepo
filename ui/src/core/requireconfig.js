require.config({
  paths: {
    angular: '/angular/angular',
    text: '/requirejs-text/text',
    ui: '/core/app',
    uiRouter: '/angular-ui-router/release/angular-ui-router',
		uiRouterExtCore: '/ui-router-extras/release/modular/ct-ui-router-extras.core',
		uiRouterExtSticky: '/ui-router-extras/release/modular/ct-ui-router-extras.sticky',
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