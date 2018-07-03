app.controller("localizationListController",
    function($rootScope, $scope, $http, $location, $timeout , localizationService, languageService) {

    function init(){
        localizationService.findAll()
        .then(function(resp){
            $scope.localizations = resp.content;
        });
    }

    init();
});