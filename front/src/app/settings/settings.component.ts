import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  public showPassword() {
    document.getElementById('password').innerHTML = "hasłousera";
}
  public changeName(){
    if(document.getElementById('ident').textContent=="Show"){
      document.getElementById('ident').innerHTML="Hide"
    }
    else{
      document.getElementById('password').innerHTML = "****";
      document.getElementById('ident').innerHTML="Show";

    }
  }

}
