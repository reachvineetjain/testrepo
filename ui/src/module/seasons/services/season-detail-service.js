'use strict';
define([
  'angular'
],function(angular){
  angular
    .module('ui.seasons.services.season-detail-service',[])
    .factory('SeasonDetailService', function SeasonDetailService($http){
      return {
        gettingSeasonDetail: gettingSeasonDetail
      };

      function gettingSeasonDetail(seasonId){
        return $http.get('/module/data/seasons-detail.json')
          .success(successSeasonDetail)
          .error(failureSeasonDetail);

        function successSeasonDetail(data){
          return data.result;
        }

        function failureSeasonDetail(error){
          console.log('XHR Failed for gettingProgramList. '+error.data);
        }
      }
    });
});
