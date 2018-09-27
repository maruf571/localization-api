app.service('projectService',
    function($http) {

    var projectApi = "/api/projects";

    this.findAll = function(query) {
     if(query == undefined)
        query = '';

     return $http.get(projectApi +'/'+ query).then(function(resp){
           return resp.data;
     });
    };

    this.findOne = function(id) {
     return $http.get(projectApi + '/' + id).then(function(resp){
           return resp.data;
     });
    };


    this.submit = function(data) {

        if(data.id){
            //edit
            return $http.put(projectApi, data).then(function(resp){
                return resp.data;
            });
        }else{
            //new
            return $http.post(projectApi, data).then(function(resp){
               return resp.data;
            });
        }
    };

    this.delete = function(id) {
     return $http.delete(projectApi + '/' + id).then(function(resp){
           return resp.data;
     });
    };

});