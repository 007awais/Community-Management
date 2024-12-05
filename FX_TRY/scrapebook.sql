CREATE TABLE Resident (
    resident_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    contact VARCHAR(15),
    address VARCHAR(255)
);

CREATE TABLE PaymentRecord (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    status ENUM('Paid', 'Pending', 'Failed') DEFAULT 'Pending',
    amount DECIMAL(10, 2) NOT NULL,
    resident_id INT,
    FOREIGN KEY (resident_id) REFERENCES Resident(resident_id)
);
CREATE TABLE EventManager (
    manager_id INT AUTO_INCREMENT PRIMARY KEY,
    manager_name VARCHAR(100) NOT NULL,
    contact VARCHAR(15)
);

CREATE TABLE Event (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    description TEXT,
    event_manager_id INT,
    FOREIGN KEY (event_manager_id) REFERENCES EventManager(manager_id)
);


CREATE TABLE System_Admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    admin_name VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);




CREATE TABLE Visitor (
    visitor_id INT PRIMARY KEY AUTO_INCREMENT,
    visitor_name VARCHAR(50) NOT NULL,
    visit_date DATE NOT NULL,
    confirmation_status BOOLEAN DEFAULT FALSE,
    admin_id INT,
    FOREIGN KEY (admin_id) REFERENCES System_Admin(admin_id) ON DELETE SET NULL
);


CREATE TABLE Scheduler (
    schedule_id INT PRIMARY KEY AUTO_INCREMENT,
    visitor_id INT,
    schedule_date DATETIME,
    schedule_details VARCHAR(255),
    FOREIGN KEY (visitor_id) REFERENCES Visitor(visitor_id)
);



CREATE TABLE Maintenance_Request (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    resident_id INT,
    status VARCHAR(50),
    location VARCHAR(100),
    urgency ENUM('Low', 'Medium', 'High'),
    FOREIGN KEY (resident_id) REFERENCES Resident(resident_id)
);

CREATE TABLE Maintenance_Service_Provider (
    provider_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    contact VARCHAR(15),
    department VARCHAR(50)
);

CREATE TABLE Finance_Office (
    officer_id INT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    payment_id INT,
    FOREIGN KEY (payment_id) REFERENCES PaymentRecord(payment_id)
);


CREATE TABLE Notification (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique ID for each notification
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- Date and time of the notification
    message TEXT NOT NULL                            -- The notification message
);

ALTER TABLE Notification
ADD COLUMN title VARCHAR(255) NOT NULL AFTER notification_id,  -- Add 'title' column after 'notification_id'
ADD COLUMN receiver_id INT AFTER message,                     -- Add 'receiver_id' column after 'message'
ADD CONSTRAINT fk_receiver FOREIGN KEY (receiver_id)          -- Add foreign key constraint for 'receiver_id'
REFERENCES Resident(resident_id) ON DELETE CASCADE;           -- Link to Resident table with cascading delete

CREATE TABLE Dispute (
    dispute_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for each dispute
    first_party_name VARCHAR(255) NOT NULL,     -- Name of the first party
    second_party_name VARCHAR(255) NOT NULL,    -- Name of the second party
    description TEXT NOT NULL,                  -- Detailed description of the dispute
    filed_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Automatically records when the dispute was filed
);

CREATE TABLE Event_Fee (
    event_fee_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for each event fee record
    event_id INT NOT NULL,                        -- Event ID (Foreign Key)
    resident_id INT NOT NULL,                     -- Resident ID (Foreign Key)
    amount DECIMAL(10, 2) NOT NULL,               -- Amount of the fee
    FOREIGN KEY (event_id) REFERENCES Event(event_id), -- Foreign key referencing Event table
    FOREIGN KEY (resident_id) REFERENCES Resident(resident_id) -- Foreign key referencing Resident table
);

CREATE TABLE RSVP (
    rsvp_id INT AUTO_INCREMENT PRIMARY KEY,         -- Unique ID for each RSVP record
    resident_id INT NOT NULL,                        -- Foreign key linking to Resident table
    event_id INT NOT NULL,                           -- Foreign key linking to Event table
    status ENUM('Yes', 'No', 'Maybe') DEFAULT 'No',  -- RSVP status: Yes, No, Maybe
    rsvp_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   -- Timestamp when the RSVP was made
    FOREIGN KEY (resident_id) REFERENCES Resident(resident_id) ON DELETE CASCADE,  -- Foreign key to Resident
    FOREIGN KEY (event_id) REFERENCES Event(event_id) ON DELETE CASCADE           -- Foreign key to Event
);


CREATE TABLE Parking (
    parking_slot_number INT AUTO_INCREMENT PRIMARY KEY,  -- Automatically generated parking slot number
    vehicle_id VARCHAR(50) NOT NULL,                      -- Vehicle ID (can be a unique identifier or VIN)
    resident_id INT,                                      -- Resident ID (foreign key)
    booking_status ENUM('Booked', 'Available', 'Occupied') DEFAULT 'Available',  -- Status of the parking spot
    time_duration INT,                                    -- Time duration for the parking booking (in minutes or hours)
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,      -- Timestamp when the parking spot was booked
    FOREIGN KEY (resident_id) REFERENCES Resident(resident_id)  -- Foreign key linking to the Resident table
);

CREATE TABLE Bill_Adjustment (
    complaint_id INT AUTO_INCREMENT PRIMARY KEY,   -- Automatically generated complaint ID
    description TEXT NOT NULL,                      -- Detailed description of the complaint
    status ENUM('Pending', 'Resolved', 'In Progress') DEFAULT 'Pending',  -- Status of the complaint
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- Timestamp when the complaint was created
);


-- Insert data into Resident table
INSERT INTO Resident (name, contact, address)
VALUES 
    ('John Doe', '1234567890', '123 Maple Street'),
    ('Jane Smith', '0987654321', '456 Oak Avenue'),
    ('Alice Brown', '5678901234', '789 Pine Road'),
    ('Robert White', '2345678901', '101 Birch Street'),
    ('Emily Johnson', '3456789012', '202 Cedar Lane');

-- Insert data into PaymentRecord table
INSERT INTO PaymentRecord (status, amount, resident_id)
VALUES 
    ('Paid', 300.00, 1),
    ('Pending', 450.00, 2),
    ('Failed', 120.00, 3),
    ('Paid', 200.00, 4),
    ('Pending', 500.00, 5);

-- Insert data into EventManager table
INSERT INTO EventManager (manager_name, contact)
VALUES 
    ('Mike Johnson', '3216549870'),
    ('Linda Green', '7894561230'),
    ('Chris Black', '9876543210');

-- Insert data into Event table
INSERT INTO Event (event_name, description, event_manager_id)
VALUES 
    ('Community Meeting', 'Annual community gathering to discuss neighborhood issues.', 1),
    ('Charity Run', '5K run for charity fundraising.', 2),
    ('Movie Night', 'Outdoor movie screening for residents.', 3),
    ('Workshop on Home Safety', 'Tips and techniques for enhancing home safety.', 1);

-- Insert data into System_Admin table
INSERT INTO System_Admin (admin_name, role, email)
VALUES 
    ('Emma Wilson', 'System Administrator', 'emma.wilson@example.com'),
    ('David Clark', 'Database Administrator', 'david.clark@example.com'),
    ('Sophie Turner', 'Security Administrator', 'sophie.turner@example.com');

-- Insert data into Visitor table
INSERT INTO Visitor (visitor_name, visit_date, confirmation_status, admin_id)
VALUES 
    ('Tom Hanks', '2024-12-01', TRUE, 1),
    ('Sara Lee', '2024-12-05', FALSE, 2),
    ('Brad Pitt', '2024-12-10', TRUE, 3),
    ('Angelina Jolie', '2024-12-15', FALSE, 1);

-- Insert data into Scheduler table
INSERT INTO Scheduler (visitor_id, schedule_date, schedule_details)
VALUES 
    (1, '2024-12-01 10:00:00', 'Meeting with System Admin Emma Wilson'),
    (2, '2024-12-05 14:00:00', 'Follow-up visit with Database Admin David Clark'),
    (3, '2024-12-10 09:00:00', 'Security briefing with Security Admin Sophie Turner');

-- Insert data into Maintenance_Request table
INSERT INTO Maintenance_Request (resident_id, status, location, urgency)
VALUES 
    (1, 'Pending', 'Main Lobby', 'High'),
    (2, 'In Progress', 'Pool Area', 'Medium'),
    (3, 'Completed', 'Gym', 'Low'),
    (4, 'Pending', 'Parking Lot', 'High');

-- Insert data into Maintenance_Service_Provider table
INSERT INTO Maintenance_Service_Provider (name, contact, department)
VALUES 
    ('Jack Thomas', '4445556666', 'Electrical'),
    ('Sarah Connor', '3332221111', 'Plumbing'),
    ('Michael Scott', '7778889999', 'HVAC'),
    ('Dwight Schrute', '5556667777', 'Security');

-- Insert data into Finance_Office table
INSERT INTO Finance_Office (role, name, payment_id)
VALUES 
    ('Accountant', 'George Harris', 1),
    ('Finance Manager', 'Nancy Drew', 2),
    ('Finance Analyst', 'Oscar Martinez', 3),
    ('Chief Financial Officer', 'Angela Martin', 4);
    
    
    
    INSERT INTO Notification (title, message, receiver_id)
VALUES 
('Payment Reminder', 'Your payment is due. Please make it before the due date.', 1),
('Event Invitation', 'You are invited to the Annual Community Meeting.', 2),
('Maintenance Update', 'Your maintenance request has been resolved.', 3),
('Welcome Message', 'Welcome to the Community Management System!', 4),
('Security Alert', 'A security drill will take place tomorrow at 10 AM.', 2),
('Profile Update', 'Your profile has been successfully updated.', 5),
('Service Notification', 'The gym will be closed for maintenance on Friday.', 1),
('Payment Received', 'Your recent payment of $100 has been received successfully.', 3),
('Reminder', 'Your event registration is incomplete. Please complete it by tomorrow.', 2),
('Community Update', 'The community park will be inaugurated next week. All residents are welcome!', 4);


INSERT INTO Dispute (first_party_name, second_party_name, description) 
VALUES 
('John Doe', 'Jane Smith', 'Dispute over property ownership.'),
('Alice Johnson', 'Bob Williams', 'Conflict regarding payment settlement.'),
('Charlie Brown', 'Lucy Van Pelt', 'Argument over intellectual property rights.'),
('Sherlock Holmes', 'Dr. John Watson', 'Dispute over credit for solving a case.')
('Mario', 'Luigi', 'Argument over who gets the last mushroom.')
('Frodo Baggins', 'Samwise Gamgee', 'Disagreement about who carried the heavier load to Mount Doom.');




-- Sample Insert Data 1
INSERT INTO Event_Fee (event_id, resident_id, amount)
VALUES 
    (1, 1, 50.00);  -- Resident 1 pays 50.00 for Event 1

-- Sample Insert Data 2
INSERT INTO Event_Fee (event_id, resident_id, amount)
VALUES 
    (2, 2, 30.00);  -- Resident 2 pays 30.00 for Event 2

-- Sample Insert Data 3
INSERT INTO Event_Fee (event_id, resident_id, amount)
VALUES 
    (3, 3, 75.00);  -- Resident 3 pays 75.00 for Event 3

-- Sample Insert Data 4
INSERT INTO Event_Fee (event_id, resident_id, amount)
VALUES 
    (1, 4, 40.00);  -- Resident 4 pays 40.00 for Event 1

-- Sample Insert Data 5
INSERT INTO Event_Fee (event_id, resident_id, amount)
VALUES 
    (4, 5, 60.00);  -- Resident 5 pays 60.00 for Event 4

    
    
    INSERT INTO RSVP (resident_id, event_id, status) VALUES
(1, 1, 'Yes'),  -- John Doe RSVP'd Yes for Community Picnic
(1, 2, 'Maybe'), -- John Doe RSVP'd Maybe for Annual Meeting
(2, 3, 'Yes'),  -- Jane Smith RSVP'd Yes for Holiday Party
(3, 1, 'No'),   -- Michael Johnson RSVP'd No for Community Picnic
(4, 2, 'Yes'),  -- Emily Davis RSVP'd Yes for Annual Meeting
(2, 4, 'Yes'),  -- Jane Smith RSVP'd Yes for Sports Day
(3, 3, 'Maybe'), -- Michael Johnson RSVP'd Maybe for Holiday Party
(4, 1, 'Yes');  -- Emily Davis RSVP'd Yes for Community Picnic




INSERT INTO Parking (vehicle_id, resident_id, booking_status, time_duration, booking_time) VALUES
('ABC1234', 1, 'Booked', 120, '2024-11-25 08:00:00'),
('XYZ5678', 2, 'Occupied', 60, '2024-11-25 09:30:00'),
('LMN9876', 3, 'Available', 0, '2024-11-25 10:00:00'),
('DEF4321', 4, 'Booked', 180, '2024-11-25 11:00:00'),
('GHI6543', 1, 'Occupied', 45, '2024-11-25 13:00:00'),
('JKL3456', 5, 'Available', 0, '2024-11-25 14:00:00'),
('PQR8765', 6, 'Booked', 90, '2024-11-25 15:30:00'),
('STU6547', 7, 'Occupied', 150, '2024-11-25 17:00:00');

INSERT INTO Bill_Adjustment (description, status)
VALUES 
('Complaint regarding overcharging in the latest bill. Customer was charged extra for maintenance fees.', 'Pending'),
('Resident claims the bill amount is incorrect due to missing discounts on the last payment.', 'In Progress'),
('Complaint about the delay in adjusting the payment made for the last month.', 'Resolved'),
('Customer requests clarification on the service charges added to their recent bill.', 'Pending'),
('Complaint regarding incorrect meter reading reflected in the bill for the last quarter.', 'Resolved');



    
    
    

