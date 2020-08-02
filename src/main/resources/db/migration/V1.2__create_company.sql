create TABLE company(
    id int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    employee_number int
);

alter table employee add foreign key(company_id) REFERENCES company(id);