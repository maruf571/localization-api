import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LanguageService } from '../../language.service';

@Component({
  selector: 'language-single',
  templateUrl: './language-single.component.html',
  styleUrls: ['./language-single.component.scss']
})

export class LanguageSingleComponent implements OnInit {

  @ViewChild('languageName')
  private elementRef: ElementRef;

  private language = {};
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private languageService: LanguageService
  ) { }

  ngOnInit() {
    this.elementRef.nativeElement.focus();

    const languageId = this.activeRoute.snapshot.queryParamMap.get('languageId');
    console.log(languageId);
    if (languageId != null) {
      this.languageService.findOne(languageId).subscribe(resp => {
        this.language = resp;
      });
    }
  }

  submit(entity) {
    
    console.log(entity);

    this.languageService.submit(entity).subscribe(resp => {
      this.router.navigate(['/language/language-list']);
    });
  }
}