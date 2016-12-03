CREATE TABLE idval ( id INTEGER, value VARCHAR(64), list_values VARCHAR(256));

INSERT INTO idval VALUES (0, null, null);
INSERT INTO idval VALUES (1, '', null);
INSERT INTO idval VALUES (2, 'value2', null);
INSERT INTO idval VALUES (3, 'value3', '');
INSERT INTO idval VALUES (4, 'value4', '|');
INSERT INTO idval VALUES (5, 'value5', '||');
INSERT INTO idval VALUES (6, 'value6', 'value_');
INSERT INTO idval VALUES (7, 'value7', '|value_');
INSERT INTO idval VALUES (8, 'value8', 'value_|');
INSERT INTO idval VALUES (9, 'value9', 'value4_0||');
INSERT INTO idval VALUES (10, 'value10', 'value5_0|value5_1');
INSERT INTO idval VALUES (11, 'value11', 'value12_0||value6_2');
INSERT INTO idval VALUES (12, 'value12', 'value12_0|value12_1|value12_2');