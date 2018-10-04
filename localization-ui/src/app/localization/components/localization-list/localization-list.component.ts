import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalizationService } from '../../localization.service';
import { LanguageService } from '../../../language/language.service';

@Component({
  selector: 'localization-list',
  templateUrl: './localization-list.component.html',
  styleUrls: ['./localization-list.component.scss']
})
export class LocalizationListComponent implements OnInit {

  localizations = [];
  language = [];
  languageId = '';
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute, 
    private localizationService: LocalizationService,
    private languageService: LanguageService
  ) {}

  ngOnInit() {
    this.languageId = this.activeRoute.snapshot.queryParamMap.get('languageId');
  
    this.languageService.findOne(this.languageId).subscribe(resp => {
      console.log(resp);
      this.language = resp;
    })
    this.localizationService.findAll("language/" + this.languageId).subscribe(resp => { 
      console.log(resp);
      this.localizations = resp;
    });
  }



  addOrEdit(localizationId) {
    console.log();
    this.router.navigate(
      ['/localization/localization-single'], {
        queryParams: {
          localizationId: localizationId,
          languageId: this.languageId,
        }
      }
    );
  }
}