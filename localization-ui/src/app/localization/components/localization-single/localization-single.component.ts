import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalizationService } from '../../localization.service';

@Component({
  selector: 'localization-single',
  templateUrl: './localization-single.component.html',
  styleUrls: ['./localization-single.component.scss']
})

export class LocalizationSingleComponent implements OnInit {

  @ViewChild('customerName')
  private elementRef: ElementRef;

  localization = {};
  projectId = '';
  languageId = '';
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private localizationService: LocalizationService
  ) { }

  ngOnInit() {
    this.elementRef.nativeElement.focus();

    const localizationId = this.activeRoute.snapshot.queryParamMap.get('localizationId');
    this.projectId = this.activeRoute.snapshot.queryParamMap.get('projectId');
    this.languageId = this.activeRoute.snapshot.queryParamMap.get('languageId');

    console.log(localizationId);
    if (localizationId != null) {
      this.localizationService.findAll("project/" + this.projectId + "/language/" + this.languageId + "/localization/" +localizationId).subscribe(resp => {
        this.localization = resp;
      });
    }
  }

  submit(entity) {
    
    entity.languageId = this.languageId;
    entity.projectId = this.projectId;

    console.log(entity);
    this.localizationService.submit(entity).subscribe(resp => {
      this.router.navigate(['/localization/localization-list'], {
        queryParams: {
          languageId: this.languageId,
          projectId: this.projectId
        }
      });
    });
  }
}