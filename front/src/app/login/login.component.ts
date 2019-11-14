import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { DataService } from '../services/data.service';

@Component({ templateUrl: 'login.component.html' })
export class LoginComponent implements OnInit {

    loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;

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

    onSubmit() {
        this.submitted = true;

        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;
        this.ds.login(this.f.username.value, this.f.password.value).pipe(first()).subscribe(data => {
            this.router.navigate([this.returnUrl]);
        }, error => {
            console.log(error);
            this.loading = false;
        });
    }
}
