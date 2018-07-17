app.controller("projectSingleController",
    function($scope, close, $http, $location, $element, projectService, project) {

    function init(){

        $scope.project = {};
        if(project){
            $scope.project = project;
        }
    }

    $scope.save = function(project){
        projectService.submit(project)
        .then(function(resp){
            $element.modal('hide');
            close(resp, 500);
         })
    }

    init();
});