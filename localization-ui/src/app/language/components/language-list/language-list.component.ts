import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LanguageService } from '../../language.service';

@Component({
  selector: 'language-list',
  templateUrl: './language-list.component.html',
  styleUrls: ['./language-list.component.scss']
})
export class LanguageListComponent implements OnInit {

  private customers = [];
  constructor(
    private router: Router,
    private route: ActivatedRoute, 
    private languageService: LanguageService
  ) {}

  ngOnInit() {
    this.languageService.findAll("").subscribe(resp => { 
      console.log(resp.content);
      this.customers = resp.content;
    });
    
  }

}