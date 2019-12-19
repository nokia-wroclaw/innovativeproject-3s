import {State, Action, StateContext, Selector} from '@ngxs/store';
import {Scan} from '../models/scan';
import {ScanService} from '../services/scan.service';
import {AddScan, DeleteScan, GetScans} from '../actions/scan.action';
import {tap} from 'rxjs/operators';


@State<Scan[]>({
    name: 'scans',
    defaults: []
})

export class ScanState {
    constructor(private scanService: ScanService) { }

    @Selector()
    static getScanList(state: Scan[]) {
        return state;
    }

    @Action(GetScans)
    getScans({setState}: StateContext<Scan[]>, {payload}: GetScans) {
        return this.scanService.fetchScans(payload.email).subscribe((result) => {
            setState({
                ...result,
            });
        });
    }

    @Action(AddScan)
    addScan(ctx: StateContext<Scan[]>, {payload}: AddScan) {
        return this.scanService.addScan(payload).subscribe((result) => {
            // ctx.dispatch(new GetScans());
        });
    }

    @Action(DeleteScan)
    deleteScan(ctx: StateContext<Scan[]>, {id}: DeleteScan) {
        return this.scanService.deleteScan(id).subscribe(() => {
            // ctx.dispatch(new GetScans());
        });
    }
}
