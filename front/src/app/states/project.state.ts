import {State, Action, StateContext, Selector} from '@ngxs/store';
import {Project} from '../models/project';
import {ProjectService} from '../services/project.service';
import {AddProject, DeleteProject, GetProjects, UpdateProject} from '../actions/project.action';

export class ProjectStateModel {
    projects: Project[];
}

@State<ProjectStateModel>({
    name: 'projects',
    defaults: {
        projects: [],
    }
})

export class ProjectState {
    constructor(private projectService: ProjectService) { }

    @Selector()
    static getProjectList(state: ProjectStateModel) {
        return state.projects;
    }

    @Action(GetProjects)
    getProjects({setState, getState}: StateContext<ProjectStateModel>, {payload}: GetProjects) {
        return this.projectService.fetchProjects(payload.email).subscribe((result) => {
            const state = getState();
            setState({
                ...state,
                projects: result,
            });
        });
    }

    @Action(AddProject)
    addProject(ctx: StateContext<ProjectStateModel>, {payload, email}: AddProject) {
        console.log(payload);
        return this.projectService.addProject(payload).subscribe((result) => {
            ctx.dispatch(new GetProjects({email}));
        });
    }

    @Action(DeleteProject)
    deleteProject(ctx: StateContext<ProjectStateModel>, {id, email}: DeleteProject) {
        return this.projectService.deleteProject(id).subscribe(() => {
            ctx.dispatch(new GetProjects({email}));
        });
    }

    @Action(UpdateProject)
    updateProject(ctx: StateContext<ProjectStateModel>, {payload, id, email}: UpdateProject) {
        return this.projectService.updateProject(payload, id).subscribe((result) => {
            ctx.dispatch(new GetProjects({email}));
        });
    }
}
