app.controller("projectListController",
    function($rootScope, $scope, $http, $location, $timeout , ModalService, projectService) {

    function init(){
        projectService.findAll()
        .then(function(resp){
            $scope.projects = resp.content;
        });
    }

    $scope.showAModal = function(project) {

            ModalService.showModal({
              templateUrl: "/app/project/project-single.html",
              controller: "projectSingleController",
              inputs: {project: project}
            })
            .then(function(modal) {

              modal.element.modal();
              modal.close.then(function(result) {
                    $scope.projects.push(result);
              });
            });

          };


    init();
});