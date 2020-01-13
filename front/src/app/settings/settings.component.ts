import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  Password : String="****"; 
  ButtonName: String="Show";

  constructor() { }

  ngOnInit() {
  }
  public showPassword() {
    this.Password="haselko";
}
  public changeName(){
    if(this.ButtonName=="Show"){
        this.ButtonName="Hide";
}
    else{
      this.Password="****";
      this.ButtonName="Show";
    }
  }

}
