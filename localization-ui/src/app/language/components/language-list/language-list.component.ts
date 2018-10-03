import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LanguageService } from '../../language.service';

@Component({
  selector: 'language-list',
  templateUrl: './language-list.component.html',
  styleUrls: ['./language-list.component.scss']
})
export class LanguageListComponent implements OnInit {

  languages = [];
  projectId = '';
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private languageService: LanguageService
  ) { }

  ngOnInit() {
    const projectId = this.activeRoute.snapshot.queryParamMap.get('projectId');
    this.projectId = projectId;
    console.log(projectId);
    if (projectId != null) {
      this.languageService.findAll("?projectId=" + projectId).subscribe(resp => {
        console.log(resp);
        this.languages = resp;
      });
    }
  }


  addOrEdit(languageId) {
    this.router.navigate(
      ['/language/language-single'], {
        queryParams: {
          projectId: this.projectId,
          languageId: languageId
        }
      }
    );
  }


  navigateToLocalization(languageId) {
    this.router.navigate(
      ['/localization/localization-list'], {
        queryParams: {
          languageId: languageId
        }
      }
    );
  }


}