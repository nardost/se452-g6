-- service locations initial records
insert into service_locations
    (street_address, city, state, zip_code, meter_mac_address)
values
    ('1 E JACKSON BLVD', 'Chicago', 'IL', 60604, 'PMRGWPJGQB8540807087'),
    ('320 N MICHIGAN AVE', 'Chicago', 'IL', 60601, 'RMBZGIKHJA8540835978'),
    ('1000 N STATE ST', 'Chicago', 'IL', 60610, 'JQITUUCKRM8540850519'),
    ('100 W LAKE ST', 'Chicago', 'IL', 60601, 'JQITUUCKRM8540850519'),
    ('1530 W FOSTER AVE', 'Chicago', 'IL', 60640, 'LRGWXAIDWG8540879212'),
    ('6000 N CLARK ST', 'Chicago', 'IL', 60660, 'LINNQESTRH8540894634'),
    ('2000 W DEVON AVE', 'Chicago', 'IL', 60659, 'YJFIMADVMM8540909755'),
    ('1031 W NORTH AVE', 'Chicago', 'IL', 60642, 'EMPABKPQDV8540925202');

-- subscriptions initial records
insert into subscriptions
    (id, service_category, activation_timestamp, service_status)
values
    ('XLO1208223', 'RESIDENTIAL', '2020-05-03 00:00:00', 'ACTIVATED'),
    ('MGB1208263', 'RESIDENTIAL', '2020-05-03 00:00:00', 'ACTIVATED'),
    ('MGB1208267', 'RESIDENTIAL', '2020-05-03 00:00:00', 'ACTIVATED'),
    ('BYE1208268', 'RESIDENTIAL', '2020-05-03 00:00:00', 'ACTIVATED'),
    ('LCN1208259', 'RESIDENTIAL', '2020-05-03 00:00:00', 'ACTIVATED'),
    ('LCN1208239', 'RESIDENTIAL', '2020-05-03 00:00:00', 'ACTIVATED'),
    ('LCN1208229', 'RESIDENTIAL', '2020-05-03 00:00:00', 'ACTIVATED'),
    ('LCN1208219', 'RESIDENTIAL', '2020-05-03 00:00:00', 'ACTIVATED');