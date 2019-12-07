import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { DataService } from '../services/data.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {

    loginForm: FormGroup;
    loading = false;
    returnUrl: string;
    loginError = false;
    errorMsg: string;

    constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router, private ds: DataService) {
        if (this.ds.currentUserValue) {
            this.router.navigate(['/']);
        }
    }

    ngOnInit() {
        this.loginForm = this.fb.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    get f() { return this.loginForm.controls; }

    get username() { return this.loginForm.get('username'); }
    get password() { return this.loginForm.get('password'); }

    onSubmit() {
        if (this.loginForm.valid) {
            this.loading = true;
            this.ds.login(this.f.username.value, this.f.password.value).pipe(first()).subscribe(data => {
                this.loginError = false;
                this.router.navigate([this.returnUrl]);
            }, error => {
                if (error === 'OK') {
                    this.errorMsg = 'Incorrect login or password';
                } else {
                    this.errorMsg = 'Cannot connect to the service';
                }
                console.log(error);
                this.loading = false;
                this.loginError = true;
            });
        }
    }
}
