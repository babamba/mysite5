 --sequence

drop SEQUENCE gallery_seq;

create sequence gallery_seq
start with 1
increment by 1
MAXVALUE 9999999999;


select no, 
		   org_file_name, 
		   save_file_name, 
		   comments, 
		   file_extname,
		   file_size,
		   to_char(reg_date, 'yyyy-mm-dd hh:mi:ss' ) as regDate,
		   users_no
	from gallery
	order by regDate desc;
	
	insert into 
		gallery( no, 
				 org_file_name,
				 save_file_name,
				 comments,
				 file_extname,
				 file_size,
				 reg_date,
				 users_no
				 )
				 values(no, 
				 		org_file_name,
				 		save_file_name,
						 comments,
						 file_extname,
						 file_size,
						 sysdate,
						 users_no);
			 
