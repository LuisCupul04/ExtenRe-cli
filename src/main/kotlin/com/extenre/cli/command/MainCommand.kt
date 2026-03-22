package com.extenre.cli.command

import com.extenre.cli.command.utility.UtilityCommand
import com.extenre.library.logging.Logger
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.IVersionProvider
import java.util.*

fun main(args: Array<String>) {
    Logger.setDefault()
    CommandLine(MainCommand).execute(*args).let(System::exit)
}

private object CLIVersionProvider : IVersionProvider {
    override fun getVersion() =
        arrayOf(
            MainCommand::class.java.getResourceAsStream(
                "/com/extenre/cli/version.properties",
            )?.use { stream ->
                Properties().apply {
                    load(stream)
                }.let {
                    "ExtenRe CLI v${it.getProperty("version")}"
                }
            } ?: "ExtenRe CLI",
        )
}

@Command(
    name = "extenre-cli",
    description = ["Command line application to use ExtenRe."],
    mixinStandardHelpOptions = true,
    versionProvider = CLIVersionProvider::class,
    subcommands = [
        PatchCommand::class,
        PathesCommand::class,
        OptionsCommand::class,
        ListPatchesCommand::class,
        ListCompatibleVersions::class,
        UtilityCommand::class,
    ],
)
private object MainCommand
