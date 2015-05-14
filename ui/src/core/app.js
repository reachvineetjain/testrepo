'use strict';

define([
  'angular',
  'ui',
  'uiRouter',
 	'uiRouterExtCore',
	'uiRouterExtSticky',
  'uiSelect'
], function(angular) {
  return angular.module('ui', [
    'ui.router',
			'ct.ui.router.extras.core',
		'ct.ui.router.extras.sticky'
  ]);
});
