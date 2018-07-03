app.controller("localizationSingleController",
    function($rootScope, $scope, $http, $location, $timeout , localizationService, languageService) {

    function init(){
        languageService.findAll()
        .then(function(resp){
            $scope.languages = resp.content;
        });
    }

    $scope.save = function(localization){
        localizationService.save(localization)
        .then(function(resp){
            console.log(resp);
         })
    }
    init();
});