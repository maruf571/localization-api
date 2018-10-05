import { Component, OnInit } from '@angular/core';
import { LanguageService } from '../../../language/language.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  languages = [];
  constructor(
    private languageService: LanguageService
  ) { }

  ngOnInit() {
    // this.languageService.findAll("").subscribe(resp => {
    //   console.log(resp);
    //   this.languages = resp;
    // })
  }

}
