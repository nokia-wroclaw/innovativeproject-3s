import {State, Action, StateContext, Selector} from '@ngxs/store';
import {Project} from '../models/project';
import {ProjectService} from '../services/project.service';
import {AddProject, DeleteProject, GetProject, UpdateProject} from '../actions/project.action';


@State<Project[]>({
    name: 'users',
    defaults: []
})

export class ProjectState {
    constructor(private projectService: ProjectService) { }

    @Selector()
    static getUserList(state: Project[]) {
        return state;
    }

    @Action(GetProject)
    getUsers({setState}: StateContext<Project[]>) {
        return this.projectService.fetchProjects().subscribe((result) => {
            setState({
                ...result
            });
        });
    }

    @Action(AddProject)
    addUser(ctx: StateContext<Project[]>, {payload}: AddProject) {
        return this.projectService.addProject(payload).subscribe((result) => {
            ctx.dispatch(new GetProject());
        });
    }

    @Action(DeleteProject)
    deleteUser(ctx: StateContext<Project[]>, {id}: DeleteProject) {
        return this.projectService.deleteProject(id).subscribe(() => {
            ctx.dispatch(new GetProject());
        });
    }

    @Action(UpdateProject)
    updateUser(ctx: StateContext<Project[]>, {payload, id}: UpdateProject) {
        return this.projectService.updateProject(payload, id).subscribe((result) => {
            ctx.dispatch(new GetProject());
        });
    }
}
