INSERT INTO parking_lot (lot_id, location, capacity, occupied_spaces) VALUES
('LOT-1', 'Downtown', 50, 0),
('LOT-2', 'Airport', 100, 0),
('LOT-3', 'Airport', 150, 0),
('LOT-4', 'Airport', 200, 0),
('LOT-5', 'Airport', 250, 0);

INSERT INTO vehicle (license_plate, type, owner_name, lot_id) VALUES
('ABC-123', 'Car', 'John Doe', NULL),
('XYZ-888', 'Motorcycle', 'Jane Smith', NULL),
('CMK-511', 'Car', 'John Brian', NULL);