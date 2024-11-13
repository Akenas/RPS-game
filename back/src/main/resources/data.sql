INSERT INTO players
SELECT *
FROM ( SELECT 1 as ID,'IA',0,1) data
WHERE NOT EXISTS ( SELECT NULL
                   FROM players AS t2 );
INSERT INTO rulesets
SELECT *
FROM ( SELECT 1 as ID,'ROCK,PAPER,SCISSORS',5) data
WHERE NOT EXISTS ( SELECT NULL
                   FROM rulesets AS t2 );

INSERT INTO game_modes
SELECT *
FROM ( SELECT 1 as ID,'BO5 vs IA',1 as mode,1 UNION ALL
       SELECT 2 as ID,'BO5 vs Player',0 as mode,1 ) data
WHERE NOT EXISTS ( SELECT NULL
                   FROM game_modes AS t2 );
