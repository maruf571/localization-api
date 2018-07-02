var app = angular.module("localizationApp", [
 'ui.router',
]);

app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state('home', {
            url: '/',
            views:{
                'content@':{
                    templateUrl: '/html/home.html'
                }
            }
        })
});
