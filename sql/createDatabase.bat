
setlocal

call "c:\Program Files\PostgreSQL\8.1\bin\psql.exe" -q -f "C:\Documents and Settings\Abu Abd-Allah\My Documents\Data\Real Estate Project\workspace\CoreServer\sql\createDatabase.sql" -d rep -U postgres

endlocal
