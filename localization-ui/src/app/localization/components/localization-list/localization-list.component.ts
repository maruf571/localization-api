import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalizationService } from '../../localization.service';

@Component({
  selector: 'localization-list',
  templateUrl: './localization-list.component.html',
  styleUrls: ['./localization-list.component.scss']
})
export class LocalizationListComponent implements OnInit {

  localizations = [];
  projectId = '';
  languageId = '';
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute, 
    private localizationService: LocalizationService
  ) {}

  ngOnInit() {
    this.languageId = this.activeRoute.snapshot.queryParamMap.get('languageId');
    this.projectId = this.activeRoute.snapshot.queryParamMap.get('projectId');
    this.localizationService.findAll("language/" + this.languageId).subscribe(resp => { 
      console.log(resp);
      this.localizations = resp;
    });
  }



  addOrEdit(localizationId) {
    this.router.navigate(
      ['/localization/localization-single'], {
        queryParams: {
          localizationId: localizationId,
          languageId: this.languageId,
          projectId: this.projectId
        }
      }
    );
  }
}