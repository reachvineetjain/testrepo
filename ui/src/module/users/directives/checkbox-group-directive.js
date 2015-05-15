'use strict';

define([
  'angular'
], function (angular) {
  angular.module('ui.userManagement.directive.checkboxGroup', []).directive("checkboxGroup", function($filter) {
    return {
      restrict: "A",
      link: function(scope, elem, attrs) {
        // Determine initial checked boxes
        if (scope.user.permissions) {
          if (scope.user.permissions.indexOf(scope.permission.id) !== -1) {
            elem[0].checked = true;
          }
        }

        // Update array on click
        elem.bind('click', function() {
          var index = scope.user.permissions.indexOf(scope.permission.id);
          // Add if checked
          if (elem[0].checked) {
            if (index === -1) scope.user.permissions.push(scope.permission.id);
          }
          // Remove if unchecked
          else {
            if (index !== -1) scope.user.permissions.splice(index, 1);
          }
          // Sort and update DOM display
          scope.$apply(scope.user.permissions.sort(function(a, b) {
            return a - b
          }));
        });

        // Update checkboxes when Role is selected
        // When Role is selected, update the user's permissions array
        scope.$watch('user.role', function(newValue) {
          if (!!newValue) {
            var selectedRole = $filter('filter')(scope.roles, {id: newValue})[0];
            scope.user.permissions = selectedRole.CCIStaffroles_DefaultResourcePermissions;

            elem[0].checked = false;
            if (scope.user.permissions.indexOf(scope.permission.id) !== -1) {
              elem[0].checked = true;
            }
          }
        });
      }
    }
  });
});
