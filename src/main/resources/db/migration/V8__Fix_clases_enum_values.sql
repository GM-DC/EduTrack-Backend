-- Migración: Corregir valores de enum en tabla clases
-- ClassMode: LIVE -> VIRTUAL, SELF_PACED -> HYBRID
-- RecurrenceEndType: AFTER_OCCURRENCES -> COUNT

UPDATE clases SET modalidad = 'VIRTUAL' WHERE modalidad = 'LIVE';
UPDATE clases SET modalidad = 'HYBRID'  WHERE modalidad = 'SELF_PACED';

UPDATE clases SET recurrence_end_type = 'COUNT' WHERE recurrence_end_type = 'AFTER_OCCURRENCES';

