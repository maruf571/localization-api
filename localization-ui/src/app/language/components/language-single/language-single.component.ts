import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LanguageService } from '../../language.service';

@Component({
  selector: 'language-single',
  templateUrl: './language-single.component.html',
  styleUrls: ['./language-single.component.css']
})

export class LanguageSingleComponent implements OnInit {

  @ViewChild('languageName')
  private elementRef: ElementRef;

  language:any = {};
  projectId = "";
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private languageService: LanguageService
  ) { }

  ngOnInit() {
    this.elementRef.nativeElement.focus();

    const languageId = this.activeRoute.snapshot.queryParamMap.get('languageId');
    this.projectId = this.activeRoute.snapshot.queryParamMap.get('projectId');
    console.log(this.projectId);
    console.log(languageId);
    if (languageId != null) {
      this.languageService.findOne(languageId).subscribe(resp => {
        console.log(resp);
        this.language = resp;
      });
    }
  }

  submit(entity) {
    //add project id
    entity.project = {id: this.projectId};

    console.log(entity);
    this.languageService.submit(entity).subscribe(resp => {
      this.router.navigate(
        ['/language/language-list'],
        {
          queryParams:{projectId: this.projectId}
        }
      );

    });
  }
}
