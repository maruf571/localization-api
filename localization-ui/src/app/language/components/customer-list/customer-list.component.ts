import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../../customer.service';

@Component({
  selector: 'customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.scss']
})
export class CustomerListComponent implements OnInit {

  private customers = [];
  constructor(
    private router: Router,
    private route: ActivatedRoute, 
    private customerService: CustomerService
  ) {}

  ngOnInit() {
    this.customerService.findAll("").subscribe(resp => { 
      console.log(resp.content);
      this.customers = resp.content;
    });
    
  }

}