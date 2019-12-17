import {State, Action, StateContext, Selector} from '@ngxs/store';
import {User} from '../models/user';
import {UserService} from '../services/user.service';
import {AddUser, DeleteUser, GetUsers, UpdateUser} from '../actions/user.action';
import {tap} from 'rxjs/operators';


@State<User[]>({
    name: 'users',
    defaults: []
})

export class UserState {
    constructor(private userService: UserService) { }

    @Selector()
    static getUserList(state: User[]) {
        return state;
    }

    @Action(GetUsers)
    getUsers({setState}: StateContext<User[]>) {
        return this.userService.fetchUsers().subscribe((result) => {
            setState({
                ...result
            });
        });
    }

    @Action(AddUser)
    addUser(ctx: StateContext<User[]>, {payload}: AddUser) {
        return this.userService.addUser(payload).subscribe((result) => {
            ctx.dispatch(new GetUsers());
        });
    }

    @Action(DeleteUser)
    deleteUser(ctx: StateContext<User[]>, {id}: DeleteUser) {
        return this.userService.deleteUser(id).subscribe(() => {
            ctx.dispatch(new GetUsers());
        });
    }

    @Action(UpdateUser)
    updateUser(ctx: StateContext<User[]>, {payload, id}: UpdateUser) {
        return this.userService.updateUser(payload, id).subscribe((result) => {
            ctx.dispatch(new GetUsers());
        });
    }
}
