'use strict';
define([
    'angular'
],function(angular){
    angular
        .module('ui.seasons.services',[])
        .factory('SeasonServices', function SeasonServices($http){
            return {
                gettingProgramList: gettingProgramList
            };

            function gettingProgramList(){
                return $http.get('/module/data/programs-listing.json')
                    .success(successProgramList)
                    .error(failureProgramList);

                function successProgramList(data){
                    return data.result;
                }

                function failureProgramList(error){
                    console.log('XHR Failed for gettingProgramList. '+error.data);
                }
            }
        });
});