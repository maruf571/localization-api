import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LocalizationService } from '../../localization.service';
import { LanguageService } from '../../../language/language.service';
import { saveAs } from 'file-saver';
import { HttpEventType, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'localization-list',
  templateUrl: './localization-list.component.html',
  styleUrls: ['./localization-list.component.scss']
})
export class LocalizationListComponent implements OnInit {

  localizations = [];
  language = [];
  languageId = '';
  fileToUpload: File = null;
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



  export(localizationId){
      console.log(localizationId);
      this.localizationService.export(localizationId).subscribe(data => {
        console.log(data);
        saveAs(data, 'YourFileName.xlsx', { 
            type: data.type 
        })
     })
  }

  selectFile(event) {
    this.uploadFile(event.target.files);
  }

  uploadFile(files: FileList) {
    if (files.length == 0) {
      console.log("No file selected!");
      return
    }
    let file: File = files[0];

    this.localizationService.uploadFile(this.languageId, file)
      .subscribe(
        event => {
          if (event.type == HttpEventType.UploadProgress) {
            const percentDone = Math.round(100 * event.loaded / event.total);
            console.log(`File is ${percentDone}% loaded.`);
          } else if (event instanceof HttpResponse) {
            console.log('File is completely loaded!');
          }
        },
        (err) => {
          console.log("Upload Error:", err);
        }, () => {
          console.log("Upload done");
        }
      )
  }
}