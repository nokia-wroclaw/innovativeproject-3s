import {State, Action, StateContext, Selector} from '@ngxs/store';
import {Scan} from '../models/scan';
import {ScanService} from '../services/scan.service';
import {AddScan, DeleteScan, GetScans} from '../actions/scan.action';
import {tap} from 'rxjs/operators';

export class ScanStateModel {
    scans: Scan[];
}

@State<ScanStateModel>({
    name: 'scans',
    defaults: {
        scans: []
    }
})

export class ScanState {
    constructor(private scanService: ScanService) { }

    @Selector()
    static getScanList(state: ScanStateModel) {
        return state.scans;
    }

    @Action(GetScans)
    getScans({getState, setState}: StateContext<ScanStateModel>) {
        return this.scanService.fetchScans().pipe(tap((result) => {
            const state = getState();
            setState({
                ...state,
                scans: result,
            });
        }));
    }

    @Action(AddScan)
    addScan({getState, patchState}: StateContext<ScanStateModel>, {payload}: AddScan) {
        return this.scanService.addScan(payload).pipe(tap((result) => {
            const state = getState();
            patchState({
                scans: [...state.scans, result]
            });
        }));
    }

    @Action(DeleteScan)
    deleteScan({getState, setState}: StateContext<ScanStateModel>, {id}: DeleteScan) {
        return this.scanService.deleteScan(id).pipe(tap(() => {
            const state = getState();
            const filteredArray = state.scans.filter(item => item.id !== id);
            setState({
                ...state,
                scans: filteredArray
            });
        }));
    }
}
