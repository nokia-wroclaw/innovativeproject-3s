import { State, Selector, StateContext, Action } from '@ngxs/store';
import { User } from '../models/user';
import { LoginService } from '../services/login.service';
import { Login, Logout } from '../actions/login.action';
import { tap } from 'rxjs/operators';

@State<User>({
  name: 'auth'
})

export class LoginState {
    constructor(private loginService: LoginService) {}

    @Selector()
    static token(state: User) {
        return state.token;
    }

    @Selector()
    static userDetails(state: User) {
        return {
            id: state.id,
            token: state.token,
            email: state.email,
            username: state.username,
            created: state.created,
            type: state.type
        };
    }

    @Action(Login)
    login({ patchState }: StateContext<User>, {payload}: Login) {
        return this.loginService.login(payload.username, payload.password).pipe(
            tap(result => {
                patchState({
                    id: result.id,
                    token: 'token', // TODO: zmiana
                    email: result.email,
                    username: result.username,
                    created: result.created,
                    type: result.type
                });
            })
        );
    }

    @Action(Logout)
    logout({ patchState }: StateContext<User>) {
        return this.loginService.logout().pipe(
            tap(_ => {
                patchState({
                    id: null,
                    token: null,
                    email: null,
                    username: null,
                    password: null,
                    created: null,
                    type: null
                });
            })
        );
    }
}
