import { Scan } from './scan';

export interface Project {
    id: number;
    name: string;
    scans: Scan[];
    status: string;
}
