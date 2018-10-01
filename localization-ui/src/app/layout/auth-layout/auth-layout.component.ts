import { Component, OnInit} from '@angular/core';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-auth-layout',
  templateUrl: './auth-layout.component.html',
  styleUrls: ['./auth-layout.component.css']
})
export class AuthLayoutComponent implements OnInit{

  constructor(
    authService: AuthService
  ){}

  ngOnInit() {
  
  }
}
