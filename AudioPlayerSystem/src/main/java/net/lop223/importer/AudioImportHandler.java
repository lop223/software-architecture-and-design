package net.lop223.importer;

import net.lop223.Track;

public interface AudioImportHandler {
    Track handle(String filePath);
}
