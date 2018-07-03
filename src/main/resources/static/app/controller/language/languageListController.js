app.controller("languageListController",
    function($rootScope, $scope, $http, $location, $timeout , languageService) {

    function init(){
        languageService.findAll()
        .then(function(resp){
            $scope.languages = resp.content;
        })
    }

    init();
});