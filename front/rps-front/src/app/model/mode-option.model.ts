import { Ruleset } from "./ruleset.model";

export interface ModeOption {
    id: number;
    type: string;
    ruleset: Ruleset;
    name: string;
  }