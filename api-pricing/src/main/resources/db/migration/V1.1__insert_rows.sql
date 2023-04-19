INSERT INTO `commercial_policy` (`id`, `active`, `name`, `priority`, `start_date`, `expire_date`) VALUES ('1', 1, 'General', '10', '2023-01-01 00:00:00', '2023-01-01 00:00:00');
INSERT INTO `commercial_policy` (`id`, `active`, `name`, `priority`, `start_date`, `expire_date`) VALUES ('2', 1, 'Special Carrier', '5', '2023-01-01 00:00:00', '2022-05-01 00:00:00');
INSERT INTO `commercial_policy` (`id`, `active`, `name`, `priority`, `start_date`, `expire_date`) VALUES ('3', 1, 'Ciber Week', '1', '2022-01-19 18:00:00', '2025-08-26 00:00:00');


INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('1', 'AR', '5', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('2', 'AA', '2', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('3', 'IB', '2', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('4', 'AL', '1', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('5', 'AF', '0', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('6', 'BA', '3', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('7', 'DL', '4', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('8', 'KL', '1', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('9', 'LH', '8', '1');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('10', 'QF', '7', '1');

INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('11', 'AR', '10', '2');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('12', 'AA', '1', '2');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('13', 'IB', '3', '2');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('14', 'AL', '1', '2');

INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('15', 'AF', '0', '3');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('16', 'BA', '1', '3');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('17', 'DL', '2', '3');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('18', 'KL', '0', '3');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('19', 'LH', '6', '3');
INSERT INTO `commercial_policy_carrier` (`id`, `carrier`, `percentage`, `commercial_policy_id`) VALUES ('20', 'QF', '5', '3');
