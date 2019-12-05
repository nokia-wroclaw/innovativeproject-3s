import {State, Action, StateContext, Selector} from '@ngxs/store';
import {User} from '../models/user';
import {UserService} from '../services/user.service';
import {AddUser, DeleteUser, GetUsers, UpdateUser} from '../actions/user.action';
import {tap} from 'rxjs/operators';

export class UserStateModel {
    users: User[];
}

@State<UserStateModel>({
    name: 'users',
    defaults: {
        users: []
    }
})

export class UserState {
    constructor(private userService: UserService) { }

    @Selector()
    static getUserList(state: UserStateModel) {
        return state.users;
    }

    @Action(GetUsers)
    getUsers({getState, setState}: StateContext<UserStateModel>) {
        return this.userService.fetchUsers().pipe(tap((result) => {
            const state = getState();
            setState({
                ...state,
                users: result,
            });
        }));
    }

    @Action(AddUser)
    addUser({getState, patchState}: StateContext<UserStateModel>, {payload}: AddUser) {
        return this.userService.addUser(payload).pipe(tap((result) => {
            const state = getState();
            patchState({
                users: [...state.users, result]
            });
        }));
    }

    @Action(DeleteUser)
    deleteUser({getState, setState}: StateContext<UserStateModel>, {id}: DeleteUser) {
        return this.userService.deleteUser(id).pipe(tap(() => {
            const state = getState();
            const filteredArray = state.users.filter(item => item.id !== id);
            setState({
                ...state,
                users: filteredArray
            });
        }));
    }

    @Action(UpdateUser)
    updateUser({getState, setState}: StateContext<UserStateModel>, {payload, id}: UpdateUser) {
        return this.userService.updateUser(payload, id).pipe(tap((result) => {
            const state = getState();
            const userList = [...state.users];
            const userIndex = userList.findIndex(item => item.id === id);
            userList[userIndex] = result;
            setState({
                ...state,
                users: userList,
            });
        }));
    }
}
