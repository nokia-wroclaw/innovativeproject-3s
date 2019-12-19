import {State, Action, StateContext, Selector} from '@ngxs/store';
import {Tool} from '../models/tool';
import {ToolService} from '../services/tool.service';
import {AddTool, DeleteTool, GetTools} from '../actions/tool.action';
import {tap} from 'rxjs/operators';


@State<Tool[]>({
    name: 'tools',
    defaults: []
})

export class ToolState {
    constructor(private toolService: ToolService) { }

    @Selector()
    static getToolList(state: Tool[]) {
        return state;
    }

    @Action(GetTools)
    getTools({setState}: StateContext<Tool[]>) {
        return this.toolService.fetchTools().subscribe((result) => {
            setState({
                ...result,
            });
        });
    }

    @Action(AddTool)
    addTool(ctx: StateContext<Tool[]>, {payload}: AddTool) {
        return this.toolService.addTool(payload).subscribe((result) => {
            ctx.dispatch(new GetTools());
        });
    }

    @Action(DeleteTool)
    deleteTool(ctx: StateContext<Tool[]>, {id}: DeleteTool) {
        return this.toolService.deleteTool(id).subscribe(() => {
            ctx.dispatch(new GetTools());
        });
    }
}
