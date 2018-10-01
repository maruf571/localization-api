import { Component, OnInit} from '@angular/core';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-public-layout',
  templateUrl: './public-layout.component.html',
  styleUrls: ['./public-layout.component.css']
})
export class PublicLayoutComponent implements OnInit{

  islogin:boolean = false;
  constructor(
    private authService: AuthService
  ){}

  ngOnInit() {
    if(this.authService.isLoggedIn()){
      console.log(this.authService.isLoggedIn());
      this.islogin = true;
    }
  }
}
