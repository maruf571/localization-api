import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../../customer.service';

@Component({
  selector: 'customer-single',
  templateUrl: './customer-single.component.html',
  styleUrls: ['./customer-single.component.scss']
})

export class CustomerSingleComponent implements OnInit {

  @ViewChild('customerName')
  private elementRef: ElementRef;

  private customer = {};
  constructor(
    private router: Router,
    private activeRoute: ActivatedRoute,
    private customerService: CustomerService
  ) { }

  ngOnInit() {
    this.elementRef.nativeElement.focus();

    const customerId = this.activeRoute.snapshot.queryParamMap.get('customerId');
    console.log(customerId);
    if (customerId != null) {
      this.customerService.findOne(customerId).subscribe(resp => {
        this.customer = resp;
      });
    }
  }

  submit(entity) {
    
    console.log(entity);

    this.customerService.submit(entity).subscribe(resp => {
      this.router.navigate(['/customer/customer-list']);
    });
  }
}