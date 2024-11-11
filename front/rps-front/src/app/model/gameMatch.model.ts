import { ModeOption } from "./mode-option.model";
import { Player } from "./player.model";
import { Round } from "./roud.model";

export interface GameMatch {
    id: string;
    player1: Player;
    player2: Player;
    mode: ModeOption;
    winner: number;
    rounds: Round[];
    ongoing:boolean;
  }